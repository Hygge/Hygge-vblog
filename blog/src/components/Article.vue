<template>
  <div id="content" class="content">
    <section id="posts" class="posts-expand">
      <article class="post post-type-normal" itemscope="" itemtype="http://schema.org/Article">
        <div class="post-date">
          <div class="post-month"> 12月</div>
          <div class="post-day"> 24</div>
        </div>
        <div class="post-badge">
                                        <span itemprop="about" itemscope="" itemtype="http://schema.org/Thing">
                                            <a href="/categories/datastructuresandalgorithms" itemprop="url"
                                               rel="index">
                                                <span itemprop="name">数据结构和算法</span>
                                            </a>
                                        </span>
        </div>

        <div class="post-block">
          <link itemprop="mainEntityOfPage" href="/archives/bubble-selection">
          <header class="post-header">
            <h1 class="post-title" itemprop="name headline">
              <a class="post-title-link" href="/archives/bubble-selection" itemprop="url"
                 style="text-decoration: none;">冒泡排序、选择排序和插入排序</a>
            </h1>
            <div class="post-meta">
                                            <span class="post-time" style="color: #00a7e0">
                                                <span class="post-meta-item-icon"><i
                                                    class="fa fa-calendar-o"></i></span>
                                                <span class="post-meta-item-text">发表于</span>
                                                <time title="Post created" itemprop="dateCreated datePublished"
                                                      datetime="Dec 24, 2021, 4:18:32 PM">
                                                    2021-12-24
                                                </time>
                                            </span>
              <span class="post-wordcount">
                                                <span class="post-meta-divider">•</span>
                                                <span class="post-meta-item-icon">
                                                    <i class="far fa-file-word"></i>
                                                </span>
                                                <span class="post-meta-item-text">字数统计</span>
                                                <span title="字数统计">4193</span>
                                            </span>
              <span class="leancloud_visitors" style="color:#ff3f1a">
                                                <span class="post-meta-divider">•</span>
                                                <span class="post-meta-item-icon"><i class="far fa-eye"></i></span>
                                                <span class="post-meta-item-text">被</span>
                                                <span class="leancloud-visitors-count">4</span>
                                                <span class="post-meta-item-text">人看爆</span>
                                            </span>
            </div>
          </header>

          <div class="post-body" itemprop="articleBody">
            <p><img alt="冒泡排序、选择排序和插入排序" src="../assets/imgs/cute.jpg" class="zoomify"></p>
            <p style="text-indent:2em;">冒泡排序冒泡排序（Bubble
              Sort），是一种计算机科学领域的较简单的排序算法。需求：排序前：{4,5,6,3,2,1}排序后：{1,2,3,4,5,6}排序原理：比较相邻的元素。如果前一个元素比后一个元素大，就交换这两个元素的位置。对每一对相邻元素做同样的工作，从开始第一对元素到结尾的最后一对</p>
            <div class="post-button text-center">
              <a class="btn post-more-btn" href="#" rel="contents">
                阅读全文 »
              </a>
            </div>
          </div>
          <footer class="post-footer">

          </footer>
        </div>

      </article>
      <article class="post post-type-normal" itemscope="" itemtype="http://schema.org/Article" v-for="(article,index) in articles" :key="index">
        <div class="post-date">
          <div class="post-month"> 12月</div>
          <div class="post-day"> 24</div>
        </div>
        <div class="post-badge">
        <span itemprop="about" itemscope="" itemtype="http://schema.org/Thing">
          <a href="/categories/datastructuresandalgorithms" itemprop="url" rel="index">
            <span itemprop="name">数据结构和算法</span>
          </a>
        </span>
        </div>

        <div class="post-block">
          <link itemprop="mainEntityOfPage" href="/archives/bubble-selection">
          <header class="post-header">
            <h1 class="post-title" itemprop="name headline">
              <a class="post-title-link" href="/archives/bubble-selection" itemprop="url"
                 style="text-decoration: none;">冒泡排序、选择排序和插入排序</a>
            </h1>
            <div class="post-meta">
              <span class="post-time" style="color: #00a7e0">
                <span class="post-meta-item-icon"><i
                    class="fa fa-calendar-o"></i></span>
                <span class="post-meta-item-text">发表于</span>
                <time title="Post created" itemprop="dateCreated datePublished"
                      datetime="Dec 24, 2021, 4:18:32 PM">
                  2021-12-24
                </time>
              </span>
              <span class="post-wordcount">
                <span class="post-meta-divider">•</span>
                <span class="post-meta-item-icon">
                  <i class="far fa-file-word"></i>
                </span>
                <span class="post-meta-item-text">字数统计</span>
                <span title="字数统计">4193</span>
              </span>
              <span class="leancloud_visitors" style="color:#ff3f1a">
                <span class="post-meta-divider">•</span>
                <span class="post-meta-item-icon"><i class="far fa-eye"></i></span>
                <span class="post-meta-item-text">被</span>
                <span class="leancloud-visitors-count">4</span>
                <span class="post-meta-item-text">人看爆</span>
              </span>
            </div>
          </header>

          <div class="post-body" itemprop="articleBody">
            <p><img alt="冒泡排序、选择排序和插入排序" src="../assets/imgs/cute.jpg" class="zoomify"></p>
            <p style="text-indent:2em;">冒泡排序冒泡排序（Bubble
              Sort），是一种计算机科学领域的较简单的排序算法。需求：排序前：{4,5,6,3,2,1}排序后：{1,2,3,4,5,6}排序原理：比较相邻的元素。如果前一个元素比后一个元素大，就交换这两个元素的位置。对每一对相邻元素做同样的工作，从开始第一对元素到结尾的最后一对</p>
            <div class="post-button text-center">
              <a class="btn post-more-btn" href="#" rel="contents">
                阅读全文 »
              </a>
            </div>
          </div>
          <footer class="post-footer">

          </footer>
        </div>

      </article>
    </section>
    <template>
      <el-pagination background layout="prev, pager, next" :page-size="5" :total="20">
      </el-pagination>
    </template>
  </div>

</template>

<script>
export default {
  name: "Article",
  components: {},
  data(){
    return{
      articles: [],
    }
  },
  methods:{

  },
  created() {

  }
}
</script>

<style scoped>
#posts {
  /*border: red solid 1px;*/
}
</style>