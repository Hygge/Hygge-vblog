<template>
  <div>
    <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
      保存并发布
    </el-button>

    <el-drawer
        title="文章设置"
        :visible.sync="drawer"
        size="30%">
    </el-drawer>

  </div>
</template>

<script>
export default {
  name: "Setting",
  components: {},
  data(){
    return{
      drawer: false,
    }
  },
  methods: {

  },
}
</script>

<style scoped>

</style>