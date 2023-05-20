import { create } from "zustand";
import { devtools, persist } from "zustand/middleware";
import * as resultData from "../api/placeSuggestion";

interface NavListItem {
  name: string;
  key: string;
}
interface AppState {
  navList: NavListItem[];
}
interface SearchValue {
  searchValue: string;
  setSearchValue: (searchValue: string) => void;
}
interface ArticlState {
  currentPage: number;
  totalPages: number;
  pageSize: number;
  data: any[];
  setPage: (page: number) => void;
  setPageSize: (pageSize: number) => void;
  setTotalPage: (totalPages: number) => void;
  showBlockPage: () => void;
  nextBlockPage: () => void;
  prevBlockPage: () => void;
  showTransPage: () => void;
  nextTransPage: () => void;
  goToNewestPage: () => void;
  goToOldestPage: () => void;
}
interface AppActions {
  search: (input: string) => void;
}

export const useNavStore = create<AppState>()(
  devtools(
    persist(
      (set) => ({
        navList: [
          { name: "home", key: "/" },
          { name: "Transactions", key: "trans" },
          { name: "L1 -> L2 Transactions", key: "l1l2trans" },
          { name: "L2 -> L1 Transactions", key: "l2l1trans" },
          { name: "L1 Transactions batches", key: "l1tb" },
          { name: "View L1 state batches", key: "vl1sb" },
          { name: "L2 Transactions", key: "l2t" },
          { name: "L2 Transactions batches", key: "l2tb" },
          { name: "View L2 state batches", key: "vl2sb" },
          { name: "ERC 20 top tokens", key: "e2tt" },
          { name: "ERC 20 transfer", key: "e2t" },
          { name: "ERC 721 top tokens", key: "e7tt" },
          { name: "ERC 721 transfer", key: "e7t" },
          { name: "ERC 1155 top tokens", key: "e1tt" },
          { name: "ERC 1155 transfer", key: "e1t" },
          { name: "Daily transaction chart", key: "dtc" },
        ],
      }),
      {
        name: "navList",
      }
    )
  )
);
export const useArticleState = create<ArticlState>()(
  devtools(
    persist(
      (set) => ({
        currentPage: 0,
        totalPages: 0,
        pageSize: 25,
        data: [
          {
            hash: "1",
            block: "1",
            age: "1",
            from: "1",
            to: "1",
            confirmBy: "1",
            value: "1",
            txnfee: "1",
          },
        ],
        setPage: (page: number) => set({ currentPage: page }),
        setPageSize: (pageSize: number) => set({ pageSize }),
        setTotalPage: (totalPages: number) => set({ totalPages }),
        showBlockPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Block(currentPage, pageSize);
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        nextBlockPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Block(
              currentPage + 1,
              pageSize
            );
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        prevBlockPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Block(
              currentPage - 1,
              pageSize
            );
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        showTransPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Transactions(
              currentPage,
              pageSize
            );
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        nextTransPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Transactions(
              currentPage + 1,
              pageSize
            );
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        prevTransPage: async () => {
          try {
            const { currentPage, pageSize } = useArticleState.getState();
            const data = await resultData.FetchL2Block(
              currentPage - 1,
              pageSize
            );
            return data;
          } catch (error) {
            console.error("An error occurred while fetching data:", error);
            return [];
          }
        },
        goToNewestPage: () => {
          set((state) => {
            return { currentPage: state.totalPages - 1 };
          });
        },
        goToOldestPage: () => {
          set({ currentPage: 0 });
        },
      }),
      {
        name: "ArticlState",
      }
    )
  )
);
export const useSearchValue = create<SearchValue & AppActions>()(
  devtools(
    persist(
      (set) => ({
        searchValue: "abc",
        setSearchValue: (searchValue: string) =>
          set((state) => ({ ...state, searchValue: searchValue })),
        search: (input) => {
          if (/^0x[a-fA-F0-9]{40}$/.test(input)) {
            // Input matches wallet regex
            return resultData.FetchL2TransactionTxid;
          } else if (/^([A-Fa-f0-9]{64})$/.test(input)) {
            // Input matches txid regex
            return resultData.FetchL2TransactionTxid;
          } else if (/^\d+$/.test(input)) {
            // Input is a number
          } else {
            // Invalid input
            console.log("Invalid input");
          }
        },
      }),

      {
        name: "searchValue",
      }
    )
  )
);
