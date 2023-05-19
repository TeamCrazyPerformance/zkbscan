class Layer2Layer {
  layer1TxHash: string;
  layer2TxHash: string;

  constructor(layer1TxHash: string, layer2TxHash: string) {
    this.layer1TxHash = layer1TxHash;
    this.layer2TxHash = layer2TxHash;
  }
}

class Layer1toLayer2 extends Layer2Layer {
  blockNo: number;
}

class Layer2toLayer1 extends Layer2Layer {}
