import styles from "./priceBox.module.css";

type ComponentProps = {
  data: {
    icon: string;
    blocknumber: string;
    transhash: number;
    walletsend: string;
    walletreceive: string;
    timestamp: string;
    currency: string;
  };
};

function MainTrans({ data }: ComponentProps) {
  return (
    <div>
      <img src={data.icon} alt="Logo" />
      <div>
        <div>{data.blocknumber}</div>
        <div>{data.transhash}</div>
      </div>

      <div>
        <div>From:{data.walletsend}</div>
        <div>To : {data.walletreceive}</div>
      </div>
    </div>
  );
}

export default MainTrans;
