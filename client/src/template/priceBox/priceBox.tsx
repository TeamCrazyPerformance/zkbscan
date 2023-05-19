import styles from "./priceBox.module.css";

type ComponentProps = {
  logo: string;
  title: string;
  price: number;
  altprice: number;
  flucrate: number;
};

function priceBox({ logo, title, price, altprice, flucrate }: ComponentProps) {
  return (
    <div>
      <div className={styles.wrap}>
        <img src={logo} alt="Logo" />
      </div>
      <div>{title} PRICE</div>
      <div>
        <div>{price}</div>
        <div>{altprice}</div>
        <div>{flucrate}</div>
      </div>
    </div>
  );
}

export default priceBox;
