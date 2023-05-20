import { useEffect, useState } from "react";

type DataType = {
  data: [
    {
      hash: "string";
      type: 0;
      amount: "string";
      info: "string";
      status: 0;
      index: 0;
      gasFeeAssetId: 0;
      gasFee: "string";
      nftIndex: 0;
      collectionId: 0;
      assetId: 0;
      assetName: "string";
      nativeAddress: "string";
      extraInfo: "string";
      memo: "string";
      accountIndex: 0;
      l1Address: "string";
      nonce: 0;
      expireAt: 0;
      blockHeight: 0;
      createdAt: 0;
      verifyAt: 0;
      stateRoot: "string";
      toAccountIndex: 0;
      toL1Address: "string";
    }
  ];
  pagination: {
    page: 0;
    size: 0;
    totalElements: 0;
    totalPages: 0;
  };
};

function L2T() {
  const [result, setResult] = useState<DataType>();

  useEffect(() => {
    const api = async () => {
      const data = await fetch(
        "http://49.247.30.69:8080/tx/l2?page=23&size=25",
        {
          method: "GET",
        }
      );
      const jsonData = await data.json();
      setResult(jsonData);
    };

    api();
  }, []);
  return (
    <div>
      <div>
        {result?.data.map((item) => (
          <div key={item.hash}>{item.accountIndex}</div>
        ))}
      </div>
    </div>
  );
}

export default L2T;
