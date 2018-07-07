## Merkle tree in functional style

Merkle trees are typically implemented as binary trees where each non-leaf node is a hash of the two nodes below it.  
The leaves can either be the data itself or a hash/signature of the data.

[![Build Status](https://travis-ci.org/arukavytsia/merkle-tree.svg)](https://travis-ci.org/arukavytsia/merkle-tree)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/arukavytsia/merkle-tree/blob/master/LICENSE)

## Overview

<img src="https://i.stack.imgur.com/2Ep7y.png&amp;h=263">

Merkle trees is often used in distributed systems for file integrity/verification purposes.

Merkle trees are used in the next projects:

   Databases:
   - [Apache Cassandra](https://www.allthingsdistributed.com/2007/10/amazons_dynamo.html)
   - [Amazon Dynamo DB](https://wiki.apache.org/cassandra/AntiEntropy)
   - [Riak](http://docs.basho.com/riak/kv/2.2.3/learn/concepts/active-anti-entropy/)
   
   Cryptocurrencis:
   - [Bitcoin](https://bitcoin.org/en/glossary/merkle-tree)
   - [Ethereum](https://github.com/ethereum/wiki/wiki/Patricia-Tree) (Modified Merkle tree)
   
   VCS:
   - [Git](https://en.wikipedia.org/wiki/Git)
   - [Mercurial](https://en.wikipedia.org/wiki/Mercurial)
   
   File systems:
   - [IPFS](https://en.wikipedia.org/wiki/InterPlanetary_File_System)
   - [Btrfs](https://en.wikipedia.org/wiki/Btrfs)
   - [ZFS](https://en.wikipedia.org/wiki/ZFS)
   
   And other:
   - [Apache Wave](https://en.wikipedia.org/wiki/Apache_Wave) (previously Google Wave)
    
