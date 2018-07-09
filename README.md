## Merkle tree in functional style

Merkle trees are typically implemented as binary trees where each non-leaf node is a hash of the two nodes below it.  
The leaves can either be the data itself or a hash/signature of the data.

[![Build Status](https://travis-ci.org/arukavytsia/merkle-tree.svg)](https://travis-ci.org/arukavytsia/merkle-tree)
[![License](https://img.shields.io/badge/license-GNU-green.svg)](https://github.com/arukavytsia/merkle-tree/blob/master/LICENSE)

## API

The whole picture:

```scala
  import com.github.merkle.conversion.Conversion._
  
  // Anything convertible to a sequence of bytes, e.g. protobuf/avro/parquet 
  val leaf1 = leaf("1", "MD5")
  val leaf2 = "c81e728d9d4c2f636f067f89cc14862c"
  val leaf3 = "3" getBytes
  val leaf4 = Array[Byte](59, 66, 1, 55) 
    
  val leafs: Seq[BlockView] = Seq(leaf1, leaf2, leaf3, leaf4)
  
  val tree = MerkleTree(leafs, "MD5")
  TreeTraverse.inorderRecursive(tree)
  val root = tree.rootHash
```

Convenient API for leafs:

- Hashes: 
```scala 
    val hashLeaf = "c81e728d9d4c2f636f067f89cc14862c"
```
- String with digest:
```scala 
    val stringWithDigest = leaf("1", "MD5")
```
- Bytes + postfix notation:
```scala 
    val bytes1 = "3".getBytes()
    val bytes2 = "3" getBytes
    val bytes3 = Array[Byte](59, 66, 1, 55)
```

- Anything which can be converted to bytes with [twitter bijection](https://github.com/twitter/bijection), for example:

- thrift/protobuf/avro
- `GZippedBytes` / `GZippedBase64String`
- `Base64String`
- `java.nio.ByteBuffer`

## Overview

<img src="https://i.stack.imgur.com/2Ep7y.png&amp;h=263">

Merkle trees are typically implemented as binary trees where each non-leaf node is a hash of the two nodes below it.
The leaves can either be the data itself or a hash/signature of the data.

Merkle trees is often used in distributed systems for file integrity/verification purposes:

   - Databases:
     [Apache Cassandra](https://www.allthingsdistributed.com/2007/10/amazons_dynamo.html),
     [Amazon Dynamo DB](https://wiki.apache.org/cassandra/AntiEntropy),
     [Riak](http://docs.basho.com/riak/kv/2.2.3/learn/concepts/active-anti-entropy/)
   
   - Cryptocurrencis:
     [Bitcoin](https://bitcoin.org/en/glossary/merkle-tree),
     [Ethereum](https://github.com/ethereum/wiki/wiki/Patricia-Tree) (modified Merkle tree)
   
   - VCS:
    [Git](https://en.wikipedia.org/wiki/Git),
    [Mercurial](https://en.wikipedia.org/wiki/Mercurial)
   
   - File systems: 
   [IPFS](https://en.wikipedia.org/wiki/InterPlanetary_File_System),
   [Btrfs](https://en.wikipedia.org/wiki/Btrfs), 
   [ZFS](https://en.wikipedia.org/wiki/ZFS)
   
   - And other: [Apache Wave](https://en.wikipedia.org/wiki/Apache_Wave) (previously Google Wave)
    
