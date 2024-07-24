<script setup lang="ts">
import { ref } from 'vue'

const dialog = ref(false);
</script>

<template>
  <v-dialog
          v-model="dialog"
          max-width="500"
        >
          <template v-slot:activator="{ props: activatorProps }">
            <v-btn
              class="text-none"
              color="primary"
              prepend-icon="mdi-plus"
              rounded="lg"
              slim
              text="등록"
              variant="flat"
              v-bind="activatorProps"
            ></v-btn>
          </template>
          <v-card
            prepend-icon="mdi-cash"
          >
            <v-card-text>
              <v-text-field
                label="티커"
                required
                variant="outlined"
              ></v-text-field>
              <v-text-field
                label="수량"
                required
                variant="outlined"
              ></v-text-field>
              <v-text-field
                label="주문 금액"
                required
                prefix="$"
                variant="outlined"
              ></v-text-field>
              <v-text-field
                label="주문일"
                required
                type="date"
                variant="outlined"
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                text="취소"
                variant="plain"
                @click="dialog = false"
              ></v-btn>
              <v-btn
                color="primary"
                text="저장"
                variant="tonal"
                @click="dialog = false"
              ></v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
</template>

<style scoped>

</style>
