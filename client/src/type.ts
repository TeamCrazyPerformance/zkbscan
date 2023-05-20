export type l1l2trans = {
  data: {
    l1Txid: string;
    l2Txid: string;
    l1BlockNumber: number;
    l2BlockHeight: number;
    l1Address: string;
    l2Address: string;
    l1Timestamp: number;
    l2CreatedAt: number;
    l2VerifyAt: number;
  }[];
  pagination: {
    page: 0;
    size: 0;
    totalElements: 0;
    totalPages: 0;
  };
};

export type l2trans = {
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
