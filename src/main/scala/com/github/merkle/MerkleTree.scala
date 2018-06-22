package com.github.merkle

/**
  * Binary immutable tree of hashes
  * @param hash root hash value
  * @param left left subtree
  * @param right right subtree
  */
case class MerkleTree(hash: Block,
                      left: Option[MerkleTree] = Option.empty,
                      right: Option[MerkleTree] = Option.empty)
