<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="card" style="margin: 20px;">
          <div class="card-body">
            <img :src="$store.state.user.photo" alt=" 用户头像" style="width: 100%;">
          </div>
        </div>
      </div>

      <div class="col-9">
        <div class="card" style="margin-top: 20px;">
          <div class="card-header">
            我的 Bot
            <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal"
                    data-bs-target="#add-bot-btn">
              <span style="font-size: 130%;">创建 Bot</span>
            </button>

            <div class="modal fade" id="add-bot-btn" tabindex="-1">
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Create Bot</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="add-bot-title" class="form-label">名称</label>
                      <input v-model="botadd.title" type="text" class="form-control" id="add-bot-title"
                             placeholder="请输入 bot 标题">
                    </div>
                    <div class="mb-3">
                      <label for="add-bot-descript" class="form-label">描述</label>
                      <input v-model="botadd.descript" type="text" class="form-control" id="add-bot-descript"
                             placeholder="请输入Bot介绍">
                    </div>
                    <div class="mb-3">
                      <label for="add-bot-code" class="form-label">代码</label>
                      <VAceEditor
                          v-model:value="botadd.content"
                          @init="editorInit"
                          lang="c_cpp"
                          theme="textmate"
                          style="height: 300px" />
                    </div>
                  </div>
                  <div class="modal-footer">
                    <div class="error-message">{{ botadd.error_message }}</div>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="add_bot">创建</button>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="card-body">
            <table class="table table-striped table-hover">
              <thead>
              <tr>
                <td>Bot Name</td>
                <td>Create Time</td>
                <td>操作</td>
              </tr>
              </thead>
              <tbody>
              <tr v-for="bot of bots" :key="bot.id">
                <td>{{ bot.title }}</td>
                <td>{{ bot.createtime }}</td>
                <td>
                  <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal"
                          :data-bs-target="'#update-bot-modal-' + bot.id">
                    修改
                  </button>
                  <button type="button" class="btn btn-warning" @click="remove_bot(bot)">删除</button>

                  <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h1 class="modal-title fs-5" id="exampleModalLabel">Create Bot</h1>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          <div class="mb-3">
                            <label for="add-bot-title" class="form-label">名称</label>
                            <input v-model="bot.title" type="text" class="form-control" id="add-bot-title"
                                   placeholder="请输入 bot 标题">
                          </div>
                          <div class="mb-3">
                            <label for="add-bot-descript" class="form-label">描述</label>
                            <input v-model="bot.description" type="text" class="form-control" id="add-bot-descript"
                                   placeholder="请输入Bot介绍">
                          </div>
                          <div class="mb-3">
                            <label for="add-bot-code" class="form-label">代码</label>
                            <VAceEditor
                                v-model:value="bot.content"
                                @init="editorInit"
                                lang="c_cpp"
                                theme="textmate"
                                style="height: 300px" />
                          </div>
                        </div>
                        <div class="modal-footer">
                          <div class="error-message">{{ bot.error_message }}</div>
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                          <button type="button" class="btn btn-primary" @click="update_bot(bot)">保存修改</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {reactive, ref} from "vue";
import $ from "jquery";
import {useStore} from "vuex";
import {Modal} from "bootstrap/dist/js/bootstrap";
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

export default {
  name: "UserBotIndexView",
  components: {VAceEditor},
  setup() {

    ace.config.set(
        "basePath",
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

    const store = useStore();
    let bots = ref([]);
    const botadd = reactive({
      title: "",
      descript: "",
      content: "",
      error_message: "",
    });

    function refresh_bot() {
      $.ajax({
        url: "http://localhost:3000/user/bot/getList/",
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          bots.value = resp;
        },
        error(resp) {
          console.log(resp);
        },
      });
    }

    function add_bot() {
      botadd.error_message = "";
      $.ajax({
        url: "http://localhost:3000/user/bot/add/",
        type: "POST",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          title: botadd.title,
          description: botadd.descript,
          content: botadd.content,
        },
        success(resp) {
          if (resp.error_message === "success") {
            botadd.title = "";
            botadd.descript = "";
            botadd.content = "";
            botadd.error_message = "";

            Modal.getInstance("#add-bot-btn").hide();

            refresh_bot();
          } else {
            botadd.error_message = resp.error_message;
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    }

    function update_bot(bot) {
      botadd.error_message = "";
      $.ajax({
        url: "http://localhost:3000/user/bot/update/",
        type: "POST",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          bot_id: bot.id,
          title: bot.title,
          description: bot.description,
          content: bot.content,
        },
        success(resp) {
          if (resp.error_message === "scuess") {

            Modal.getInstance('#update-bot-modal-' + bot.id).hide();

            refresh_bot();
          } else {
            botadd.error_message = resp.error_message;
          }
        },
        error(resp) {
          console.log(resp);
        },
      });
    }

    function remove_bot(bot) {
      $.ajax({
        url: "http://localhost:3000/user/bot/remove/",
        type: "POST",
        data: {
          bot_id: bot.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            refresh_bot();
          }
        },
      });
    }

    refresh_bot();

    return {
      bots,
      refresh_bot,
      botadd,
      add_bot,
      remove_bot,
      update_bot,
    };
  }
}
</script>

<style scoped>
.error-message {
  color: red;
}
</style>