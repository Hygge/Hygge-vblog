<template>
  <div class="tag">
    <el-card shadow="always">
      个人信息
      <hr/>
      <el-avatar :size="100" :src=user.avator></el-avatar>
      <br/>

    </el-card>

  </div>
</template>

<script>
export default {
  name: "Person",
  components:{},
  data(){
    return{
      user:{},
    }
  },
  methods:{

  },
  created() {
    let userInfo = this.$store.getters.getUser
    console.log(userInfo)
    this.user = userInfo


  }
}
</script>

<style scoped>
.el-card{
  margin-top: 100px;
  margin-right: 10px;
  float: right;
  width: 350px;
  height: 500px;
  padding: 10px;
  text-align: center;

  /*透明度*/
  /*opacity:0.1;*/
}
.el-avatar{
  text-align: center;
}

</style>