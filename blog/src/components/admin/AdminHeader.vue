<template>
  <div>
    <h2>欢迎 {{ user.userName }}!!</h2>
    <div class="avatar">
      <router-link :to="{name: 'Index'}">
        <el-avatar :size="150" :src=user.avator></el-avatar>
      </router-link>
    </div>
    <hr/>
    <span>
    <el-link type="primary" href="#/admin" :underline="false">主页</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="success" href="#/admin/blogs" :underline="false">文章</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="danger" href="#/admin/AdCategory" :underline="false">分类</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="warning" href="#/admin/tag" :underline="false">标签</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="primary" href="#/admin/AdContent" :underline="false">评论</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="success" href="#/admin/AdHuisouzan" :underline="false">回收站</el-link>
    </span>
    <el-divider direction="vertical"></el-divider>
    <span>
      <el-link type="danger" href="#/admin/userInfo" :underline="false">个人设置</el-link>
    </span>
  </div>
</template>

<script>
export default {
  name: "AdminHeader",
  component: { },
  data(){
    return {
     user:{
       userName:"hygge",
       avator: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F13302548523%2F641.jpg&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646656711&t=5532d2f0901b47fed40c86c127bb7812",
     },

    }
  },
  methods:{

  },
  created() {
    //加载个人信息
    this.user = this.$store.getters.getUser

  }
}
</script>

<style scoped>
a{
  text-decoration: none;
  font-size: 16px;
}

</style>