<template>
  <div class="blogs">
    <AdminHeader/>
    <el-card class="box-card">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="categorysId">
          <!-- 只贴出item部分 -->
            <el-select v-model="ruleForm.categorysId" placeholder="请选择类别" size="medium">
              <el-option  v-for="(item, id) in categorys" :key="id" :label="item.name"
                          :value="item.id"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-checkbox-group v-model="ruleForm.tag">
            <el-checkbox v-for="(item,i) in tags" :key="i"  :value="item.id" :label="item.tagName"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <mavon-editor
              v-model="ruleForm.content"
              ref=md
              @imgAdd="$imgAdd"

              style="min-height: 500px;"
          />
          <!--          @imgDel="$imgDel"-->
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即保存</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
<!--        <Setting/>-->
      </el-form>
    </el-card>
  </div>
</template>

<script>
import AdminHeader from "@/components/admin/AdminHeader";
// import Setting from "@/components/admin/Setting";
export default {
  name: "BlogEdit",
  components: {
    // Setting,
    AdminHeader,
  },
  data(){
    return {
      // 表单数据
      ruleForm: {
        id: 0,
        title: '',
        description: '',
        content: '',
        categorysId: '',
        tag: [],
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请填写摘要', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请填写内容', trigger: 'blur' }
        ],
      },

      // 分类
      categorys: { },
      // 标签数据
      tags: { },
    };
  },
  methods: {

    // 检查内容是否为空并保存服务器
    submitForm(formName) {
      let _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 提交 保存文章至服务器
          // console.log(_this.ruleForm.content)
          // console.log(_this.ruleForm.title)
          console.log(_this.ruleForm.tag)
          _this.$http.post("/blog/edit",_this.ruleForm).then(res => {
            console.log(res.data)
            // 保存成功跳转主页
            _this.$router.push("/admin/blogs")
            _this.$message.success("发布成功")
          })

        } else {
          _this.$message.error("保存失败")
          return false;
        }
      });
    },
    //重置清空内容
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //将图片上次至服务器，返回地址替换到md中
    // 绑定@imgAdd event
    $imgAdd(pos, $file){
      let _this = this
      // 第一步.将图片上传到服务器.
      let formdata = new FormData();
      formdata.append('multipartFile', $file);
      let fileName = $file.name.split(".")[0]
      formdata.append('fileName', fileName+'_')
      console.log(fileName)
      _this.$http({
        url: '/uploadImage',
        method: 'post',
        data: formdata,
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then(res => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         */
        console.log(res.data.data)
        _this.$refs.md.$img2Url(pos, res.data.data);
      })
    },
    // 根据blogId获取文章编辑
    getBlog(id){
      let _this = this
      _this.$http.get("/blog/"+id).then(res => {
        _this.ruleForm = res.data.data
        _this.ruleForm.content = res.data.data.context
        console.log(res.data.data)
      })
    },
    // 获取所有分类
    getAllCategorys(){
      let _this = this
      _this.$http("/getAllCategorys").then(res => {
        _this.categorys = res.data.data
      })
    },
    // 获取所有标签
    getAllTag(){
      let _this = this
      _this.$http("/getAllTag").then(res => {
        _this.tags = res.data.data
      })
    },

  },
  created() {
    let id = this.$route.params.blogId
    if (id){
      this.getBlog(id)
    }
    this.getAllCategorys()
    this.getAllTag()
  },
}
</script>

<style scoped>
.el-form{
  padding: 10px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}

.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}

</style>