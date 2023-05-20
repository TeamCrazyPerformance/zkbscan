import { useSearchValue } from "../../store/store";
import style from "./Header.module.css";

function Header() {
  const setSearchValue = useSearchValue((state) => state.setSearchValue);
  return (
    <div className={style.Container}>
      <label htmlFor="site-search"></label>
      <div className="input-group mb-3">
        <input
          type="text"
          className="form-control input-text rounded"
          placeholder="Search Block Number, Adress, Hash..."
          onChange={(e) => {
            setSearchValue(e.target.value);
          }}
        />
        <div className={style.Space}></div>
        <div className="input-group-append">
          <button className="btn btn-outline-warning btn-lg" type="button">
            <i className="fa fa-search"></i>
          </button>
        </div>
      </div>
    </div>
  );
}

export default Header;
