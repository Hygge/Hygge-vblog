<template>
  <div>
    <div>
      <el-dialog
          title="文件上传"
          :visible.sync="show"
          :center="true"
          :show-close="false"
          :close-on-click-modal="true"
          class="aupload"
      >
        <el-upload
            class="upload-demo"
            drag
            ref="businessLicense"
            action
            :auto-upload="false"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :on-change="uploadSectionFile"
            multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传图片类型文件</div>
        </el-upload>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "Aupload",
  data() {
    return {
      show:false,
      fileList: [],
    };
  },
  methods: {
    handlePreview(file) {
      console.log(file);
    },
    beforeRemove(file, fileList) {
      console.log(fileList.size())
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleRemove(file){
      console.log(file)
    },
    uploadSectionFile(file){
      const typeArr = ['image/png', 'image/gif', 'image/jpeg', 'image/jpg'];
      const isJPG = typeArr.indexOf(file.raw.type) !== -1;
      // const isLt3M = file.size / 1024 / 1024 < 3;
      if (!isJPG) {
        this.$message.error('只能是图片!');
        this.$refs.upload.clearFiles();
        this.fileList = null;
        return;
      }
      this.fileList = file.raw;
      // FormData 对象
      var formData = new FormData();
      // 文件对象
      formData.append('files', this.fileList);
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        methods: 'post'
      }
      this.$http.post("/uploadList",formData,config)
    }
  },
  props: ["status"],
  watch:{
    status(val) {
      this.show = val;
    },
    show(val) {
      this.$emit("update:status", val);
    }
  }
}
</script>

<style scoped>
.aupload{
  width: 80%;
  margin: 0 auto;
}
.upload-demo{
  max-width: 400px;
  margin: 0 auto;
}
</style>
