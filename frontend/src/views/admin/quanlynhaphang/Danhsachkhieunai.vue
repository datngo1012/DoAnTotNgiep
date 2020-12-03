<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>QUẢN LÝ NGƯỜI DÙNG</h4>
      </v-app-bar>
      <div id="app">
        <v-row no-gutters class="align-start justify-start">
          <v-data-table
            :headers="tableHeaders"
            :items="requests"
            :items-per-page="10"
            class="elevation-1"
            :loading="loading"
            loading-text="Loading... Please wait"
            :search="search"
            :sort-by="['req_id']"
            :sort-desc="[true]"
          >
            <!-- Concatenate template with template code -->
            <template
              v-slot:item.templateConcatenated="{ item }"
            >{{item.template}} ({{item.template_cohort}})</template>
            <!-- append mailto link to the requester -->
            <template v-slot:item.requester="{ item }">
              <a
                v-bind:href="'mailto:'+item.requester_email+''"
                style="text-decoration:none; color:#fff"
              >{{item.requested_by}}</a>
            </template>
            <!-- display buttons and bind them to their respective functions -->
            <template v-slot:item.action="{ item }">
              <v-btn
                color="success"
                @click="createPacks(item)"
                :loading="item.createloading"
                :disabled="item.createloading"
              >Create</v-btn>
              <v-btn
                color="error"
                @click="excludeRequest(item)"
                :loading="item.cancelloading"
                :disabled="item.cancelloading"
              >Cancel</v-btn>
            </template>
          </v-data-table>
        </v-row>

        <v-row no-gutters>
          <div class="error w-100 text-center" v-if="error">
            <p class="mt-3">{{ error }}</p>
            <p>
              <button class="btn btn-warning mb-3" @click.prevent="fetchData()">Try Again</button>
            </p>
          </div>
        </v-row>
      </div>
    </v-container>
  </v-app>
</template>

<script>
import { USERS } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Naptien",
  data() {
    return {
      search: "",
      loading: null,
      requests: [
        {
          req_id: 78,
          no_batches: 3,
          batch_size: 10,
          requested_at: "05 September 2019, 11:19:34",
          requested_by: "First Last",
          requester_email: "xx@xx.com",
          complete: 0,
          producer: "-",
          produced_at: "-",
          template: "Strides",
          template_id: 4,
          template_cohort: "RTB",
          exclude: 0,
          excluded_at: "-",
          excluded_by: "-"
        },
        {
          req_id: 79,
          no_batches: 4,
          batch_size: 10,
          requested_at: "05 September 2019, 11:19:37",
          requested_by: "Last First",
          requester_email: "xx@xx.com",
          complete: 0,
          producer: "-",
          produced_at: "-",
          template: "Strides",
          template_id: 4,
          template_cohort: "RTB",
          exclude: 0,
          excluded_at: "-",
          excluded_by: "-"
        }
      ],
      error: null,
      tableHeaders: [
        {
          text: "ID",
          value: "req_id",
          align: "center"
        },
        { text: "Template", value: "templateConcatenated" },
        { text: "No of batches", value: "no_batches" },
        { text: "Batch size", value: "batch_size" },
        { text: "Date requested", value: "requested_at" },
        { text: "Requested by", value: "requester" },
        { text: "Actions", value: "action", sortable: false, align: "center" }
      ],
      createloading: false,
      cancelloading: false,
      successmessage: "",
      errormessage: ""
    };
  },
  computed: {
    ...mapGetters(["users"]),
    layDanhSachNguoiDungs: function() {
      return this.users.map(user => {
        return user.login;
      });
    }
  },
  methods: {},
  created() {
    this.$store.dispatch(USERS).catch();
  }
};
</script>
