package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L2Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface L2BlockRepository extends JpaRepository<L2Block, BigInteger> {

    L2Block findFirstByOrderByHeightDesc();
}
