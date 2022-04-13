<template>
  <header class="header">
    <div class="header-inner">
<!--      导航栏-->
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#/">Hygge</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="#/index">首页</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/guidang">文章归档</a>
            </li>
            <li class="nav-item dropdown">
              <el-dropdown :hide-on-click="false" >
                <span class="nav-link">
                  <a href="#/category" style="text-decoration: none;color: gray;"> 分类</a>
                  <el-icon class="el-icon--right">
                    <arrow-down/>
                  </el-icon>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item v-for="(item,index) in categorizes" :key="index"
                                      v-text="item.name" @click.native="getCategoryOne(item.id)">
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>

            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#/about">关于页面</a>
            </li>
          </ul>

          <div class="header-input">
            <el-input class="w-50 m-2" placeholder="Type something">
              <template #prefix>
                <el-icon class="el-input__icon"><search /></el-icon>
              </template>
            </el-input>
          </div>
        </div>

      </nav>


    </div>
  </header>
</template>

<script>

export default {
  name: "Header",
  components: { },
  data() {
    return {
      categorizes:[],
      blogs:[],
    }
  },
  methods: {
    // 点击分类
    getCategoryOne(id){

      console.log(id);
    },
    // 获取所有分类
    getAllCategorys(){
      let _this = this
      _this.$http("/getAllCategorys").then(res => {
        _this.categorizes = res.data.data
      })
    },

  },
  created() {
    this.getAllCategorys()

  }
}
</script>

<style scoped>
.header {
  position: relative;
  margin: 0 auto;
  width: 100%;
  background: 0 0;
  top: -60px;

}
.navbar{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.header-inner {
  position: relative;
  width: 100%;
  height: 30px;
  padding: 0;
  box-shadow: initial;
  border-radius: initial;

}

.collapse {
  margin-left: 5%;
}


.nav-link{
  font-size: 16px;
  color: black;
}
.header-input{
  width: 20%;
}
</style>