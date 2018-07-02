package com.github.merkle.utils

import com.github.merkle.MerkleTree
import org.scalatest._

trait UnitSpec extends FlatSpec
    with Matchers
    with BeforeAndAfter {

  def getRootValue(tree: MerkleTree)= tree.hash.left.get
}