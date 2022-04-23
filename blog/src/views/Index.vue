<template>
  <div id="index" >
    <Header/>


    <div class="site-brand-wrapper" :style="{backgroundImage: 'url(' + coverImgUrl + ')', backgroundSize:'cover'}">
        <Person/>
    </div>
    <br/>
      <div class="content">
        <!--      统计-->
        <Count/>
        <!--      友情链接-->
        <LinkFriend/>
        <!--        文章列表-->
        <div class="main-inner">
          <div class="content-wrap">
            <el-card class="acard" shadow="hover" v-for="(blog, key) in blogs" :key="key">
              <h2>
                <router-link :to="{name: 'BlogInfo', params: {blogId: blog.id}}"
                  target="_blank"
                >
                  {{blog.title}}
                </router-link>
              </h2>
              <el-divider direction="vertical"></el-divider>
              <span class="sp-text" v-for="(t, i) in blog.tag" :key="i">{{ t }}</span>
              <el-divider direction="vertical"></el-divider>
              <el-divider content-position="left"><span style="color: #E6A23C;">{{ blog.category }} </span></el-divider>
              <p>{{ blog.description }}</p>
              <br/>
              <div  class="userDetailBox">
                <a class="item el-link el-link--default"><i class="el-icon-view"></i>&nbsp;{{blog.numberView}}</a>&nbsp;
                <a class="item el-link el-link--default"><i class="el-icon-circle-check"></i>&nbsp;{{ blog.content }}</a>&nbsp;
                <a class="item el-link el-link--default"><i class="el-icon-time"></i>&nbsp;
                  {{ blog.createdDate }}</a>
              </div>
            </el-card>
          </div>

          <br/>
<!--          分页-->

          <el-pagination
              v-if="show"
              background
              layout="prev, pager, next"
              :total="total"
              :page-size="pageSize"
              :current-page.sync="current"
              @current-change=getAllPage(current)
          >
          </el-pagination>
          <br/>

        </div>



    </div>

    <Footer/>

  </div>
</template>

<script>
import LinkFriend from "@/components/LinkFriend";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Count from "@/components/Count";
import Person from "@/components/Person";
export default {
  name: 'Index',
  components: {Person, Count, Footer, Header,  LinkFriend, },
  data(){
    return{
      coverImgUrl: 'http://corehome0.oss-cn-hongkong.aliyuncs.com/images/COS%E6%B5%B7%E7%90%B4%E7%83%9F%20%E5%90%8C%E4%BA%BA%20cosplay%E7%BE%8E%E5%A5%B33440x1440%E5%B8%A6%E9%B1%BC%E5%B1%8F%E5%A3%81%E7%BA%B8_%E5%BD%BC%E5%B2%B8%E5%9B%BE%E7%BD%91_2022-02-07T11%3A42%3A27.585-168e835c-7dce-4ce4.jpg?Expires=1646032736&OSSAccessKeyId=LTAI4G3jsiM4zfyA3geoN3eX&Signature=7lNDq5YSuBveZGFKQ9DIfSJXyes%3D',
      blogs: [],
      total:0,
      pageSize: 5,
      current: 1,
      show: false,


    }
  },
  // 鼠标操作方法
  mounted() {
    window.addEventListener("scroll",this.showbtn,true);
  },
  methods:{
    //获取所有文章
    getAllPage(current){
      let _this = this
      console.log(current)
      _this.$http.post("/getAllPage",{
        current: current,
        pageSize: _this.pageSize
      }).then(res => {
        console.log(res.data.data)
        _this.blogs = res.data.data.data
        _this.total = res.data.data.count
        if (_this.total > 0){
          _this.show = true
        }
      })

    },

    //获取个人信息
    getUserInfo(){
      let _this = this
      _this.$http.get("/userInfo").then(res => {
        console.log(res.data.data)
        let userInfo = res.data.data
        // 保存个人信息
        _this.$store.commit("SET_USERINFO", userInfo)
        _this.coverImgUrl = userInfo.coverImgUrl
      })

    },

  },
  created() {

    this.getAllPage(this.current)
    this.getUserInfo()


  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.content{
  width: 100%;
}
.acard{
  width: 1000px;
  max-height: 100%;
  display: inline-block;
  margin: 10px;
}
.site-brand-wrapper {
  margin-top: -15px;
  /*position: relative;*/
  height: 650px;
  max-width: 100%;
  background-position: center;
  background-repeat: no-repeat;
}
.main-inner{
  max-width: 1100px;
  margin: 0 auto;
}
.userDetailBox{
  max-width: 100%;
  max-height: 100%;
}
.router-link-active {
  text-decoration: none;
}
.sp-text{
  font-size: 12px;
  color: #67C23A;
}

</style>
