package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L2Block;
import com.tcp.zkbscan.back.repository.L2BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
