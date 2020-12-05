<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>QUẢN LÝ NGƯỜI DÙNG</h4>
      </v-app-bar>
      <div id="app">
        <v-row>
          <table>
            <thead>
              <tr>
                <th>Tên đăng nhập</th>
                <th>Tên người dùng</th>
                <th>Số dư</th>
                <th>Danh sách đơn hàng</th>
                <th>Hành động</th>
                <th>Trạng thái</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in users" :key="user.id">
                <td>{{user.user.login}}</td>
                <td>{{user.hoTen}}</td>
                <td>{{user.soDu}}</td>
                <td style="text-align: center">
                  <v-btn color="yellow" dark>Xem</v-btn>
                </td>
                <td style="text-align: center">
                  <v-btn color="green" dark>Nộp tiền</v-btn>
                </td>
                <td style="text-align: center" v-if="user.user.activated">
                  <v-btn @click="deactived(user.user.id)" color="red" dark>Chặn</v-btn>
                </td>
                <td style="text-align: center" v-if="!user.user.activated">
                  <v-btn @click="actived(user.user.id)" color="primary" dark>Mở chặn</v-btn>
                </td>
              </tr>
            </tbody>
          </table>
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
import { USERS, ACTIVED, DEACTIVED } from "@/store/actions.type";
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
    ...mapGetters(["users"])
  },
  methods: {
    actived(id) {
      const userid = {
        userid: id
      };
      this.$store.dispatch(ACTIVED, userid).then();
      this.$store.dispatch(USERS).catch();
    },
    deactived(id) {
      const userid = {
        userid: id
      };
      this.$store.dispatch(DEACTIVED, userid).then();
      this.$store.dispatch(USERS).catch();
    }
  },
  created() {
    this.$store.dispatch(USERS).catch();
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin: 50px 10px;
}

/* Zebra striping */
tr:nth-of-type(odd) {
  background: #eee;
}

th {
  background: #3498db;
  color: white;
  font-weight: bold;
}

td,
th {
  padding: 10px;
  border: 1px solid #ccc;
  text-align: left;
  font-size: 18px;
}

/* 
Max width before this PARTICULAR table gets nasty
This query will take effect for any screen smaller than 760px
and also iPads specifically.
*/
@media only screen and (max-width: 760px),
  (min-device-width: 768px) and (max-device-width: 1024px) {
  table {
    width: 100%;
  }

  /* Force table to not be like tables anymore */
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }

  /* Hide table headers (but not display: none;, for accessibility) */
  thead tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }

  tr {
    border: 1px solid #ccc;
  }

  td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50%;
  }

  td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
    /* Label the data */
    content: attr(data-column);

    color: #000;
    font-weight: bold;
  }
}
</style>
