import { useArticleState } from "../../store/store";

function Table({ data }: { data: any }) {
  const articState = useArticleState((state) => state.showBlockPage);
  return <>{}</>;
}

export default Table;
