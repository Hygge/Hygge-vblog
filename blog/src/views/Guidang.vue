<template>
  <div id="content" class="content">
    <Header/>

    <div class="guidang">
      <h2>归档</h2>
      <h5>时间线 - 文章标题</h5>
      <div class="block">
        <el-timeline>
          <el-timeline-item :timestamp="blog.createdDate" placement="top"
                            type="success" v-for="(blog, i) in blogs" :key="i"
          >
            <el-card>
              <h6>
              <router-link :to="{name: 'BlogInfo', params: {blogId: blog.id}}"
                           target="_blank"
              >
                {{blog.title}}
              </router-link>
              </h6>
            </el-card>
          </el-timeline-item>

        </el-timeline>
      </div>
      <br/>

      <!--          分页-->
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          :current-page.sync="current"
          @current-change=getAllPage(current)
      >
      </el-pagination>
      <hr/>
      <br/>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "Guidang",
  components: {Footer, Header},
  data(){
    return{
      blogs: [],
      total:0,
      pageSize: 10,
      current: 1,

    }
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
      })

    },

  },
  created() {
    //当页面加载时获取
    this.getAllPage(this.current)

  }
}
</script>

<style scoped>
.block{
  margin: 0 auto;
  /*max-width: 650px;*/
  width: 40%;
  max-height: 100%;
}
.el-card{
  max-height: 60px;
}
.guidang{
  /*max-width: 30%;*/
  /*max-height: 100%;*/
}

</style>