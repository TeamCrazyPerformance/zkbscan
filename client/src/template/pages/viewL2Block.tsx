import { useState, useEffect } from "react"

type DataType = {
    data: [
        {
            height: 0,
            commitment: "string",
            stateRoot: "string",
            priorityOperations: 0,
            pendingOnChainOperationsHash: "string",
            pendingOnChainOperationsPubData: "string",
            committedTxHash: "string",
            committedAt: 0,
            verifiedTxHash: "string",
            verifiedAt: 0,
            transactions: [
                {
                    hash: "string",
                    type: 0,
                    amount: "string",
                    info: "string",
                    status: 0,
                    index: 0,
                    gasFeeAssetId: 0,
                    gasFee: "string",
                    nftIndex: 0,
                    collectionId: 0,
                    assetId: 0,
                    assetName: "string",
                    nativeAddress: "string",
                    extraInfo: "string",
                    memo: "string",
                    accountIndex: 0,
                    l1Address: "string",
                    nonce: 0,
                    expireAt: 0,
                    blockHeight: 0,
                    createdAt: 0,
                    verifyAt: 0,
                    stateRoot: "string",
                    toAccountIndex: 0,
                    toL1Address: "string"
                }
            ],
            "status": 0
        }
    ],
    "pagination": {
        "page": 0,
        "size": 0,
        "totalElements": 0,
        "totalPages": 0
    }
}

function ViewL2Block() {
    const [result, setResult] = useState<DataType>();

    useEffect(() => {
        const api = async () => {
            const data = await fetch(
                "http://49.247.30.69:8080/block/l2?page=1&size=1",
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
                    <div key={item.height}>{item.stateRoot}</div>
                ))}
            </div>
        </div>
    );
}

export default ViewL2Block;