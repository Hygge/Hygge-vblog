<template>
  <footer class="footer">
    <div class="footer-inner">

      <p>Copyright © 2022
        <a href=""> {{ user.userName }}</a>
        <a href="https://beian.miit.gov.cn" target="_blank">{{user.icp}}</a><br>
      </p>
      <p>
        由 <a href="http://www.hygge20.top/" target="_blank" class="external" rel="nofollow">{{user.userName}}</a> 强力驱动 · Theme by
        <a href="#" target="_blank" class="external" rel="nofollow">
          Sagiri
        </a> ·
        <a href="/sitemap.html" target="_blank" class="external" rel="nofollow">站点地图</a>
      </p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  components: { },
  data(){
    return{
      user:{ },
      icp: '赣ICP备2021011250号-1',
      userName: 'hygge',
    }
  },
  methods:{

    //获取个人信息
    getUserInfo(){
      let _this = this
      _this.$http.get("/userInfo").then(res => {
        // console.log(res.data.data)
        let userInfo = res.data.data
        // 保存个人信息
        _this.$store.commit("SET_USERINFO", userInfo)
        _this.coverImgUrl = userInfo.coverImgUrl
      })

    },
  },
  created() {
    this.getUserInfo()
    let userInfo = this.$store.getters.getUser
    console.log(userInfo)
    this.user = userInfo


  }
}

</script>

<style scoped>
.footer {
  background: #232323;
  padding: 15px 0 10px;
  text-align: center;
  color: #888;
  font-size: 12px;
  line-height: 1.5;
  /*position: absolute;*/
  left: 0;
  bottom: 0;
  width: 100%;
  min-height: 100px;
  margin: 0;
}
a:hover{
  text-decoration: none;
  color: red;
}
</style>