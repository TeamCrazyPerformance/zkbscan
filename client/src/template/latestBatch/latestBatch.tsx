import styles from "./priceBox.module.css";

type ComponentProps = {
  logo: string;
  txnbatch: string;
  statebatch: string;
  statecounter: number;
  txncounter: number;
};

function latestBatch({
  logo,
  txnbatch,
  statebatch,
  statecounter,
  txncounter,
}: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={logo} alt="Logo" />
      </div>
      <div>
        <div>{txnbatch}</div>
        <div>{txncounter}</div>
      </div>
      <div>
        <div>{statebatch}</div>
        <div>{statecounter}</div>
      </div>
    </div>
  );
}

export default latestBatch;
