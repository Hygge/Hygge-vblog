<template>
  <div>
    <AdminHeader/>
    <br/>
    <h1>信息详情</h1>
    <div class="form-info">
      <el-card shadow="always">
        <el-form ref="form" :model="user1" label-width="90px">
<!--          <el-avatar :size="130" :src=user1.avator></el-avatar>-->
          <br/>
          <br/>

          <el-upload class="avatar-uploader showUploader"
                     ref="businessLicense"
                     action
                     :auto-upload="false"
                     :on-preview="handlePreview"
                     :on-remove="handleRemove"
                     :before-remove="beforeRemove"
                     :on-change="uploadSectionFile"
                     >
            <el-avatar :size="175" v-if="photo" :src=user1.avator></el-avatar>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>

<!--          <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8181/upImg"
              multiple
              :data="{
            headers:{ 'authorization': this.$store.state.token,
          'Content-Type': 'multipart/form-data',}}"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
          </el-upload>-->
          <br/>
          <el-form-item label="昵称*用户名">
            <el-input v-model="user1.userName"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="user1.email"></el-input>
          </el-form-item>
          <el-form-item label="QQ">
            <el-input v-model="user1.qq"></el-input>
          </el-form-item>
          <el-form-item label="签名">
            <el-input v-model="user1.signature"></el-input>
          </el-form-item>
          <el-form-item label="备案号">
            <el-input v-model="user1.icp"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="saveInfo(user)" >立即保存</el-button>
          </el-form-item>
        </el-form>

      </el-card>
    </div>

  </div>
</template>

<script>
import AdminHeader from "@/components/admin/AdminHeader";
export default {
  name: "UserInfo",
  components: {AdminHeader},
  data() {
    return {
      user:{},
      user1: {},
      fileList: [],
      files: {},
      photo: '',
    }
  },
  methods:{

    saveInfo(user1){
      console.log(user1)
      let _this = this
      _this.$http.post("/upUserInfo", _this.user1).then(res => {
        console.log(res.data.data)
        let userInfo = res.data.data
        _this.$store.commit("SET_USERINFO", userInfo)
        // 刷新页面
        _this.$router.push("/admin/userInfo")
      })


    },

    //上传头像

    handlePreview(file) {
      console.log(file);
    },
    beforeRemove(file, fileList) {
      console.log(fileList.size())
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleRemove(file){
      console.log(file)
      this.user1.avator = this.user.avator

    },
    uploadSectionFile(file){
      const typeArr = ['image/png', 'image/gif', 'image/jpeg', 'image/jpg'];
      const isJPG = typeArr.indexOf(file.raw.type) !== -1;
      // const isLt3M = file.size / 1024 / 1024 < 3;
      if (!isJPG) {
        this.$message.error('只能是图片!');
        this.$refs.upload.clearFiles();
        this.files = null;
        return;
      }
/*      if (!isLt3M) {
        this.$message.error('上传图片大小不能超过 3MB!');
        this.$refs.upload.clearFiles();
        this.files = null;
        return;
      }*/
      this.files = file.raw;
      // FormData 对象
      var formData = new FormData();
      // 文件对象
      formData.append('upload', this.files);
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        methods: 'post'
      }
      this.$http.post("/upImg",formData,config).then(res=>{
        this.user1.avator = ''
        this.user1.avator = res.data.data
        this.photo = 1
        console.log(this.user1.avator)
      })
    },
    /*submitUpload() {
      console.log('上传'+this.fileList[0])
      if(this.fileList[0] == ""){
        this.$message.warning('请选择要上传的文件！')
        return false
      }
      let fileFormData = new FormData();
      fileFormData.append('multipartFile', this.fileList[0])
      fileFormData.append("name", "avator")

      this.$http({
        url:"/uploadImage",
        method: 'post',
        data: fileFormData,
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then(res => {
        console.log(res.data.data)

      })
    }*/


  },
  created() {
    let _this = this
    _this.user = _this.$store.getters.getUser
    _this.user1.avator = _this.user.avator
    _this.user1.userName = _this.user.userName
    _this.user1.email = _this.user.email
    _this.user1.qq = _this.user.qq
    _this.user1.signature = _this.user.signature
    _this.user1.icp = _this.user.icp



  }
}
</script>

<style scoped>
.form-info{
  margin: 0 auto;
  max-width: 45%;
  max-height: 100%;
}
.el-card{
  margin: 0 auto;
  padding: 10px;
}
.el-form-item{
  width: 90%;
}


.avatar-uploader /deep/ .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  background-color: #fff;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 180px;
  height: 180px;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 158px;
  height: 158px;
  line-height: 158px;
  text-align: center;
}
.showUploader /deep/ .el-upload-list{
  display: none;
}

</style>