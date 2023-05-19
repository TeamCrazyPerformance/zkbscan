package com.tcp.zkbscan.back.service;

import com.tcp.zkbscan.back.entity.L1Block;
import com.tcp.zkbscan.back.repository.L1BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class L1BlockService {

    private final L1BlockRepository l1BlockRepository;

    public L1Block getLatestBlock() {
        return l1BlockRepository.findFirstByOrderByNumberDesc();
    }

    public void saveBlock(L1Block l1Block) {
        l1BlockRepository.save(l1Block);
    }

}
