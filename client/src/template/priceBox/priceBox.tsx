import styles from "./priceBox.module.css";

type ComponentProps = {
  data: {
    logo: string;
    title: string;
    price: number;
    altprice: number;
    flucrate: number;
  };
};

function PriceBox({ data }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={data.logo} alt="Logo" />
      </div>
      <div>{data.title} PRICE</div>
      <div>
        <div>{data.price}</div>
        <div>{data.altprice}</div>
        <div>{data.flucrate}</div>
      </div>
    </div>
  );
}

export default PriceBox;
