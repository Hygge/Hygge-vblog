<template>
  <div>
    <AdminHeader/>
    <br/>
    <h2>回收站</h2>
    <el-card class="box-card">
      <el-table
          :data="tableData"
          style="width: 100%"
          max-height="100%">
        <el-table-column
            fixed
            prop="title"
            label="标题"
            width="150">
          <template slot-scope="scope">
            <router-link :to="{name: 'BlogInfo', params: {blogId: scope.row.id}}"
                         target="_blank"
            >
              {{scope.row.title}}
            </router-link>
          </template>
        </el-table-column>
        <el-table-column
            prop="status"
            label="状态"
            width="130">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.status === 1 ? 'success' : 'danger'"
                disable-transitions>
              <span>{{panduan(scope.row.status)}}</span>
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="category"
            label="分类"
            width="130">
          <template slot-scope="scope">
            <el-tag>
              {{scope.row.category}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="tag"
            label="标签"
            width="250">
          <template slot-scope="scope">
            <el-tag type="success" v-for="(item,i) in scope.row.tag" :key="i">
              {{item}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="content"
            label="评论"
            width="120">
        </el-table-column>
        <el-table-column
            prop="numberView"
            label="访问"
            width="120">
        </el-table-column>
        <el-table-column
            prop="createdDate"
            label="发布时间"
            width="150">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="200">
          <template slot-scope="scope">
          <span>
            <router-link  :to="{name: 'BlogEdit', params: {blogId: scope.row.id}}"
            >
             编辑
            </router-link>
          </span>
            <el-divider direction="vertical"></el-divider>
            <span>
          <el-button class="btn-text" type="text"  @click="modify(scope.row.id, scope.row.status)">发布</el-button>
          </span>
            <el-divider direction="vertical"></el-divider>
            <span>
          <el-link :underline="false" @click="open(scope.row.id)">删除</el-link>
          </span>
          </template>

        </el-table-column>


      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="getAllPage(current)"
          :current-page.sync="current"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="sizes, prev, pager, next"
          :total="total">
      </el-pagination>

    </el-card>


  </div>
</template>

<script>
import AdminHeader from "@/components/admin/AdminHeader";
export default {
  name: "AdHuisouzan",
  components: { AdminHeader},
  data(){
    return{
      tableData: [],
      current: 1,
      pageSize: 5,
      total: 0,

    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val
      this.getAllPage(this.current)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageSize = val
      this.getAllPage(this.current)
    },

    //判断文章状态
    panduan(status){
      if (status === 1){
        return "已发布"
      }else {
        return "回收站"
      }
    },

    //修改文章状态，变更为回收站
    modify(id, status){
      let _this = this
      console.log(id)
      let a = status === 1 ? 0 : 1
      console.log(a)
      _this.$http.post("/modifyStatus", {
        id: id,
        status: a
      }).then(res => {
        console.log(res.data.code)
        //刷新表格数据
        _this.getAllPage(_this.current)
      })

    },


    //获取所有文章
    getAllPage(current){
      let _this = this
      console.log(current)

      _this.$http.post("/admin/getAllPage",{
        current: current,
        pageSize: _this.pageSize,
        flag: 0,
      }).then(res => {
        console.log(res.data.data)
        _this.tableData = res.data.data.data
        _this.total = res.data.data.count
      })

    },

    //删除文章
    open(id) {
      let _this = this
      console.log(id)
      _this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        _this.$http.post("/delArticle/" + id).then(res => {
          console.log(res.data.code)

          _this.$message({
            type: 'success',
            message: '删除成功!'
          });
          //刷新表格数据
          _this.getAllPage(_this.current)
        });
      }).catch(() => {
        _this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }

  },

  created() {
    this.getAllPage(this.current)

  }
}
</script>

<style scoped>
.box-card{
  margin: 0 auto;
  max-width: 1300px;
  height: 100%;
}
.btn-xie{
  float: left;
}
.btn-text{
  color: #55ff00;
}

</style>