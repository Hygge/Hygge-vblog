<template>
  <div>
    <Header/>

    <br/>
    <div class="blog-text">
      <br/>
      <h2>{{ blog.title }}</h2>
      <br/>
      <p style="font-size: 16px;">
        <i class="el-icon-date"/>发表于 {{blog.createdDate}}  • <i class="el-icon-view"/> 被 {{ blog.numberView }} 人看爆
      </p>
      <span>
            <el-divider content-position="left" ><el-button type="success"  :loading="true">分类：{{blog.category}}</el-button> </el-divider>
      </span>
      <!--      博客文章-->
      <div class="markdown-body" v-html="blog.content">
      </div>

    </div>
    <br/>
    <div class="blog-text">
<!--      评论区-->
      <h5>评论区</h5>
      正在开发中
      <div class="pinglun">

        <p v-for="(item, i) in comments" :key="i"><i class="el-icon-s-promotion"></i> &nbsp;{{ item.username }}：{{ item.content }}</p>
        <p><i class="el-icon-s-promotion"></i> &nbsp;hygge: 第二条评论</p>
        <hr/>

      </div>
      <div class="pinglun">
        <h5>发表评论</h5>
        <el-form :model="comment" :rules="rules" ref="comment"  label-width="50px">
<!--          <el-form-item prop="content">-->
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入内容"
                v-model="comment.content">
            </el-input>
<!--          </el-form-item>-->
            <br/>
            <br/>
          <el-form-item label="昵称" prop="username" >
            <el-input v-model="comment.username" placeholder="请输入昵称" ></el-input>
          </el-form-item>
          &nbsp;
          &nbsp;
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="comment.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          &nbsp;
          &nbsp;
          <el-form-item label="地址" prop="address">
            <el-input v-model="comment.address" placeholder="请输入博客链接"></el-input>
          </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('comment')" round>发表评论</el-button>
            </el-form-item>
        </el-form>
        <br/>
        <br/>

      </div>

    </div>
    <br/>
    <Footer/>
  </div>
</template>

<script>
import MarkdownIt from 'markdown-it'
import 'github-markdown-css/github-markdown.css'
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "BlogInfo",
  components: {Footer, Header},
  data(){
    return {
      blog: {
/*        id: 0,
        title: '',
        description: '',
        context: '',
        numberView: 0,
        userId: 0,
        categorysId: '',
        createdDate: '',
        tag: [],
        category: '',*/
      },
      comment: {
        content: '',
        username: '',
        email: '',
        address: '',
        articleId: 0,
      },
      rules: {
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入昵称', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
      },

      comments: {},

    }
  },
  methods:{

    //发表评论
    submitForm(comment){
      console.log(comment)
      let _this = this
      _this.comment.articleId = _this.blog.id
      console.log(_this.comment)
      _this.$refs[comment].validate((valid) => {
        if (valid) {
          _this.$http.post("/saveComment", _this.comment).then(res => {
            console.log(res.data.data)

            //重新加载评论区
            _this.getComments(_this.blog.id)
            _this.$message.success("评论成功")
          })

        }else {
          _this.$message.error("发表失败")
          return false;
        }
      });
    },

    //加载评论区
    getComments(id){
      let _this = this
      _this.$http.get("/getComments/" + id).then(res => {
        _this.comments = res.data.data

      })

    },

    // 根据blogId获取文章编辑
    getBlog(id){
      let _this = this
      _this.$http.get("/blog/"+id).then(res => {
        _this.blog = res.data.data
        var md = new MarkdownIt()
        let result = md.render(_this.blog.context)
        //调整html的img标签即图片大小
        _this.blog.content = result.replace(/<img/g,"<img style='max-width:100%;height:auto;'");
        console.log(result)
      })
    },
  },
  created() {
    let id = this.$route.params.blogId
    if (id){
      this.getBlog(id)
      this.getComments(id)
    }
  },
}
</script>

<style scoped>
.blog-text{
  max-width: 1100px;
  margin: 0 auto;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 20px;
  background-color: white;
}
.blog-text h1 {
  margin-top: 20px;
  text-align: center;
}
.markdown-body{
  max-width: 1100px;
  padding: 30px;
  text-align: left;
}
.pinglun{
  margin: 0 auto;
  width: 800px;
  max-height: 100%;
  /*border: red solid 1px;*/
  text-align: left;
  font-size: 14px;
}
.el-form-item{
  display: inline-block;
}

</style>