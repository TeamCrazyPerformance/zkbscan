import styles from "./priceBox.module.css";

type ComponentProps = {
  data: {
    logo: string;
    counter: number;
    tps: string;
  };
};

function latestTrans({ data }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={data.logo} alt="Logo" />
      </div>
      <div>LATEST TRANSACTION INDEX</div>
      <div>
        <div>{data.counter}</div>
        <div>{data.tps}</div>
      </div>
    </div>
  );
}

export default latestTrans;
