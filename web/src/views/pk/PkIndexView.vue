<template>
    <PlayGround/>
</template>

<script>

import PlayGround from "@/components/PlayGround.vue";
import $ from "jquery";
import {useStore} from "vuex";

export default {
    name: "PkIndexView",
    components: {PlayGround},
  setup() {
    const store = useStore();

    $.ajax({
      url: "http://localhost:3000/user/bot/add/",
      type: "post",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      data: {
        title: "title",
        description: "description",
        content: "content",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });
  }
}
</script>

<style scoped>

</style>