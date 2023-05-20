/*
 *   Blbok
 */
export const FetchL2Block = async (
  page: number,
  size: number
): Promise<any> => {
  const url = `http://172.16.41.132:8080/block/l2?page=${page}&size=${size}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

export const FetchL2BlockHeight = async (param: number) => {
  const url = `http://172.16.41.132:8080/block/l2/${param}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

export const FetchL1BlockHeight = async (param: number) => {
  const url = `http://172.16.41.132:8080/block/l1/${param}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

export const FetchL2Transactions = async (
  page: number,
  size: number
): Promise<any> => {
  const url = `http://172.16.41.132:8080/tx/l2?page=${page}&size=${size}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

/*
 *   Transaction
 */
export const FetchL2TransactionTxid = async (param: string) => {
  const url = `http://172.16.41.132:8080/tx/l2/${param}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

export const FetchL1TransactionTxid = async (param: string) => {
  const url = `http://172.16.41.132:8080/tx/l1/${param}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

export const FetchDepositTransaction = async (
  page: number,
  size: number
): Promise<any> => {
  const url = `http://172.16.41.132:8080/tx/deposit?page=${page}&size=${size}`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};

/*
 *   Statistics
 */
export const FetchStatistics = async () => {
  const url = `http://172.16.41.132:8080/statistics`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};
export const FetchTVL = async () => {
  const url = `http://172.16.41.132:8080/statistics/tvl`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};
export const FetchTransactionChart = async () => {
  const url = `http://172.16.41.132:8080/statistics/chart`;
  const res = await (
    await fetch(url, {
      method: "GET",
    })
  ).json();

  return res;
};
