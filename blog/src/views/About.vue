<template>
  <div id="content" class="content">
    <Header/>
  <section id="posts" class="about">
            <article class="post post-type-normal" itemscope="" itemtype="http://schema.org/Article">
              <div class="post-block">
                <header class="post-header">
                  <h1 class="post-title" itemprop="name headline">
                    关于页面
                  </h1>
                </header>
                <div class="post-body" itemprop="articleBody">
                  <h2 id="关于页面">关于页面</h2>
                  <p>这是一个自定义页面，你可以在后台的 <code>页面</code> -&gt; <code>所有页面</code> -&gt; <code>自定义页面</code> 找到它，你可以用于新建关于页面、留言板页面等等。发挥你自己的想象力！</p>
                  <blockquote>
                    <p>这是一篇自动生成的页面，你可以在后台删除它。</p>
                  </blockquote>

                </div>
              </div>
            </article>
          </section>
    <Footer/>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "About",
  components: {Footer, Header}
}
</script>

<style scoped>
.main {
  padding-bottom: 130px;
}
.about{
  margin: 0 auto;
  max-width: 1200px;
}

</style>