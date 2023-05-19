import styles from "./priceBox.module.css";

type ComponentProps = {
  data: {
    logo: string;
    txnbatch: string;
    statebatch: string;
    statecounter: number;
    txncounter: number;
  };
};

function latestBatch({ data }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={data.logo} alt="Logo" />
      </div>
      <div>
        <div>{data.txnbatch}</div>
        <div>{data.txncounter}</div>
      </div>
      <div>
        <div>{data.statebatch}</div>
        <div>{data.statecounter}</div>
      </div>
    </div>
  );
}

export default latestBatch;
