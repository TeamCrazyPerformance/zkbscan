interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
function DetailOverview({ data }: MyComponentProps) {
  return (
    <div>
      <div>Transaction Hash: {data.hash}</div>
      <div>Status: {data.status}</div>
      <div>Block Number: {data.blocknum}</div>
      <div>Timestamp: {data.timestamp}</div>
      <div>From: {data.from}</div>
      <div>To: {data.to}</div>
      <div>Value: {data.value}</div>
      <div>Transaction Fee: {data.transfee} </div>
      <div>Gas Price: {data.gasprice}</div>
    </div>
  );
}

export default DetailOverview;
