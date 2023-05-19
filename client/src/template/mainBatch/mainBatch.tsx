import styles from "./priceBox.module.css";

type ComponentProps = {
  data: {
    icon: string;
    batchindex: string;
    timestamp: string;
    transhash: number;
    txncounter: number;
    txns: number;
  };
};

function MainBatch({ data }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <div>
          <img src={data.icon} alt="Logo" />
          <div>
            <div>{data.batchindex}</div>
            <div>{data.timestamp}</div>
          </div>
        </div>
      </div>
      <div>
        <div>Hash:{data.transhash}</div>
        <div>{data.txns}</div>
      </div>
    </div>
  );
}

export default MainBatch;
