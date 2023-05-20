import { create } from "zustand";
import { devtools, persist } from "zustand/middleware";
import * as resultData from "../api/placeSuggestion";

interface NavListItem {
  id: number;
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

interface PageState {
  id: number;
  setId: (id: number) => void;
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
          { id: 0, name: "home", key: "/" },
          { id: 1, name: "Transactions", key: "trans" },
          { id: 2, name: "L1 -> L2 Transactions", key: "l1l2trans" },
          { id: 3, name: "L2 -> L1 Transactions", key: "l2l1trans" },
          { id: 4, name: "L2 Transactions", key: "l2t" },
          { id: 5, name: "ERC 20 top tokens", key: "e2tt" },
          { id: 6, name: "ERC 20 transfer", key: "e2t" },
          { id: 7, name: "NFT top tokens", key: "NFTTT" },
          { id: 8, name: "NFT transfer", key: "NFTT" },
          { id: 9, name: "Daily transaction chart", key: "dtc" },
          { id: 10, name: "TVL chart", key: "dtc" },
        ],
      }),
      {
        name: "navList",
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

export const usePageState = create<PageState>()(
  devtools(
    persist(
      (set) => ({
        id: 0,
        setId: (id: number) => set((state) => ({ ...state, id: id })),
      }),
      {
        name: "id",
      }
    )
  )
);
