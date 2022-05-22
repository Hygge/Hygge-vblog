<template>
  <div>
    <AdminHeader/>
    <br/>
    <h2>附件</h2>
      <el-card class="box-card" shadow="always">
        <el-form :inline="true" v-model="searchForm"  ref="searchForm">
          <el-form-item label="关键词：">
            <el-input  v-model="searchForm.keyword"/>
          </el-form-item>
          <el-form-item label="存储类型" >
            <el-select v-model="searchForm.storage" placeholder="存储类型">
              <el-option label="本地" value="0"></el-option>
              <el-option label="云" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="文件类型">
            <el-select placeholder="文件类型" v-model="searchForm.fileType">
              <el-option v-for="(suffix, key) in fileTypes" :key="key" :label="suffix" :value="suffix"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="listImg">查询</el-button>
            <el-button type="info" @click="reset">重置</el-button>
          </el-form-item>

        </el-form>
        <el-row>
          <el-button type="primary" @click="openUpload" style="margin-left: -838px;"><i class="el-icon-upload"></i>&nbsp;&nbsp;上传</el-button>
          <el-button type="info"  @click="reset">批量操作</el-button>
          <!--        弹窗-->
          <Aupload :status.sync="uploadDialogStatus" />
        </el-row>

      </el-card>
      <br/>

      <div class="box-card" shadow="always">
          <div class="block" style="display: inline-block;margin-left: 10px;" v-for="(img, key) in imgs" :key="key">
            <el-checkbox v-model="checked" ></el-checkbox>
<!--            <el-image :src="img.fileUrl"  class="img"
                      fit="scale-down"
            ></el-image>-->
             <el-popover placement="right" title="" trigger="hover" width="500">
              <img :src="baseUrl + img.fileUrl"  />
              <img slot="reference" :src="baseUrl + img.fileUrl" :alt="baseUrl + img.fileUrl" style="width: 265px;height: 165px">
            </el-popover>
            <br/>
            <el-tooltip  >
              <div slot="content">{{ img.fileName }}</div>
              <label class="demonstration">{{ img.fileName }}</label>
            </el-tooltip>
            <br/>
          </div>
<!--        <el-table
            ref="multipleTable"
            :data="tableData"
            border
            style="width: 100%"
            >
          <el-table-column
              width="250"
              align="center"
              class="elc"
          >-->
<!--            v-for="(img, key) in imgs" :key="key"-->
<!--            <el-popover placement="right" title="" trigger="hover" width="500">
              <img :src="baseUrl + img.fileUrl" />
              <img slot="reference" :src="baseUrl + img.fileUrl" :alt="baseUrl + img.fileUrl" >
            </el-popover>
            <br/>
            <el-tooltip >
              <div slot="content">{{ img.fileName }}</div>
              <label class="demonstration">{{ img.fileName }}</label>
            </el-tooltip>-->
<!--            <template slot-scope="scope">
              <el-popover placement="right" title="" trigger="hover" width="500">
                <img :src="baseUrl + scope.row.fileUrl" />
                <img slot="reference" :src="baseUrl + scope.row.fileUrl" :alt="baseUrl + scope.row.fileUrl" >
              </el-popover>
              <br/>
              <el-tooltip >
                <div slot="content">{{ scope.row.fileName }}</div>
                <label class="demonstration">{{ scope.row.fileName }}</label>
              </el-tooltip>
            </template>
          </el-table-column>

        </el-table>-->
      </div>
    <br/>
    <!--          分页-->
    <div class="block">
      <el-pagination v-if="show"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-sizes="[20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  <br/>
  <br/>
  </div>
</template>

<script>
import AdminHeader from "@/components/admin/AdminHeader";
import Aupload from "../../components/admin/Aupload";
export default {
  name: "Annex",
  components: { AdminHeader, Aupload},
  data(){
    return{
      tableData: [],
      current: 1,
      pageSize: 20,
      total: 0,
      show: false,
      searchForm:{
        keyword: null,
        storage: null,
        fileType: null,
      },
      uploadDialogStatus: false,
      imgs: {},
      fileTypes:{},
      baseUrl: this.$baseUrl.BaseURL,
    }
  },
  methods: {
    //显示弹窗
    openUpload() {
      this.uploadDialogStatus = true;
    },
    reset(){
      this.searchForm.keyword = null;
      this.searchForm.storage = null;
      this.searchForm.fileType = null;
    },

    listImg(){
      let _this = this
      console.log(_this.current)
      let searchForm = _this.searchForm
      _this.$http.post("/listImg",{
          fileName: searchForm.keyword,
          localOrCloud: searchForm.storage,
          fileSuffixName: searchForm.fileType,
          current: _this.current,
          pageSize: _this.pageSize
      }).then(res => {
        console.log(res.data.data)
        let fileRecords = res.data.data.fileRecords
        let fileTypes = res.data.data.fileTypes
        _this.tableData = fileRecords.records
        _this.imgs = fileRecords.records
        _this.total = fileRecords.total
        _this.fileTypes = fileTypes
        if (_this.total > 0){
          _this.show = true
        }
      })

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }

  },
  created() {
    this.listImg();
  }
}
</script>

<style scoped>
.box-card{
  margin: 0 auto;
  max-width: 1100px;
  height: 100%;
}
.demonstration{
  background-color: white;
  width: 200px;
  height: 25px;
  /*隐藏过长的文件名*/
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

</style>
