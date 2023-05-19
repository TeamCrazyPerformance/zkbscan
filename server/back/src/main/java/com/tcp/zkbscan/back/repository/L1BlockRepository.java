package com.tcp.zkbscan.back.repository;

import com.tcp.zkbscan.back.entity.L1Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface L1BlockRepository extends JpaRepository<L1Block, BigInteger> {

    L1Block findFirstByOrderByNumberDesc();
}
