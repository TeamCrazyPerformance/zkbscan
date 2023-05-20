//import { useArticleState } from "../../store/store";
import style from "./Article.module.css";
import UsePage from "./handleArticle";

function Article() {
  //const articleState = useArticleState((state) => state.articleState);
  return (
    <div className={style.Container}>
      <div id="content">
        <UsePage />
      </div>
    </div>
  );
}

export default Article;
