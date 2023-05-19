import styles from "./priceBox.module.css";

type ComponentProps = {
  logo: string;
  counter: number;
  tps: string;
};

function latestTrans({ logo, counter, tps }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={logo} alt="Logo" />
      </div>
      <div>LATEST TRANSACTION INDEX</div>
      <div>
        <div>{counter}</div>
        <div>{tps}</div>
      </div>
    </div>
  );
}

export default latestTrans;
