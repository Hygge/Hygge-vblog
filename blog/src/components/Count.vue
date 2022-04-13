<template>
  <div class="tag">
    <el-card shadow="always">
      网站统计
      <hr/>
      <span><i class="el-icon-loading"></i> 总访问人数：{{count}} </span>

    </el-card>

  </div>
</template>

<script>
export default {
  name: "Count",
  components:{},
  data(){
    return{
      count: 0,
    }
  },
  methods:{

  },
  created() {
    this.count = this.$store.getters.getUser.count
  }
}
</script>

<style scoped>
.el-card{
  margin-top: 150px;
  margin-left: 20px;
  float: left;
  width: 200px;
  height: 300px;
  padding: 10px;
  display: inline-block;
}

</style>