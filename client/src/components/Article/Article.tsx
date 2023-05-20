//import { useArticleState } from "../../store/store";
import style from "./Article.module.css";

function Article() {
  //const articleState = useArticleState((state) => state.articleState);
  return (
    <div className={style.Container}>
      <div id="content"></div>
    </div>
  );
}

export default Article;
