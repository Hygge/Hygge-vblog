<template>

  <div id="content" class="content">
    <Header/>
    <h1>分类下文章标题</h1>
    <hr/>
    <br/>
    <br/>
    <Footer/>
  </div>

</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "Category",
  components: {Footer, Header},
  data(){
    return{

    };
  },
  methods:{

  },
  created() {
    // 通过分类id去查询文章

  }
}
</script>

<style scoped>


</style>