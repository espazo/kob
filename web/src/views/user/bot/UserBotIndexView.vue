<template>
    <ContentField>My Robot</ContentField>
</template>

<script>
import ContentField from "@/components/ContentField.vue";
import {useStore} from "vuex";
import $ from "jquery";

export default {
    name: "UserBotIndexView",
    components: {ContentField},
    setup() {
        const store = useStore();

        // $.ajax({
        //     url: "http://localhost:3000/user/bot/add/",
        //     type: "post",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     data: {
        //         title: "title",
        //         description: "description",
        //         content: "content",
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },
        //     error(resp) {
        //         console.log(resp);
        //     }
        // });

        // $.ajax({
        //     url: "http://localhost:3000/user/bot/remove/",
        //     type: "post",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     data: {
        //         bot_id: 1,
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },
        //     error(resp) {
        //         console.log(resp);
        //     },
        // });

        // $.ajax({
        //     url: "http://localhost:3000/user/bot/update/",
        //     type: "post",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     data: {
        //         bot_id: 1,
        //         title: "title",
        //         description: "description",
        //         content: "content",
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },
        //     error(resp) {
        //         console.log(resp);
        //     }
        // });

        $.ajax({
            url: "http://localhost:3000/user/bot/getList/",
            type: "post",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
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