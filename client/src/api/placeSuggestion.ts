export const FetchL1Block = async () => {
    const url = `http://172.16.41.132:8080/block`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL2Block = async () => {
    const url = `http://172.16.41.132:8080/block/l2?page=1&size=1`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL1BlockHeight = async () => {
    const url = `http://172.16.41.132:8080/block/l1/2131`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL2BlockHeight = async () => {
    const url = `http://172.16.41.132:8080/block/l2/1`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL2Transactions = async () => {
    const url = `http://172.16.41.132:8080/tx/l2?page=3&size=3`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL2TransactionTxid = async () => {
    const url = `http://172.16.41.132:8080/tx/l2/q`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchL1TransactionTxid = async () => {
    const url = `http://172.16.41.132:8080/tx/l1/213`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};

export const FetchDepositTransaction = async () => {
    const url = `http://172.16.41.132:8080/tx/deposit`;
    const res = await (
        await fetch(url, {
            method: 'GET',
        })
    ).json();

    return res
};