package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.repository.L2BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class L2BlockService {

    private final L2BlockRepository l2BlockRepository;

    public L2Block getLatestBlock() {
        return l2BlockRepository.findFirstByOrderByHeightDesc();
    }

    public void saveBlock(L2Block l2Block) {
        l2BlockRepository.save(l2Block);
    }

    public L2Block getBlockByHeight(BigInteger blockNumber) {
        Optional<L2Block> block = l2BlockRepository.findById(blockNumber);
        return block.get();
    }

    public Page<L2Block> getBlock(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return l2BlockRepository.findAllByOrderByHeightDesc(pageRequest);
    }

}
