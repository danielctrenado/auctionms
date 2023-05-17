# Auction MS

### Description

Auction MS allows users to auction many different kind of items, from electronic equipment to airline tickets.
Auctions proceed according to the english strategy, users continue to place bids on an item until the bid period
for that item expires and the highest bidder wins. Items will be organized in categories.
Each item can be auctioned only once. It means one item is associated to one auction.

### Use cases

1. User creates an auction.

User clicks new auction button.
Application displays new auction page.
The information about auction is
User fills information about the auction and clicks ok button.
Application displays auction was created successfully.

2. User places a bid for an auction.

User searches for an auction.
Applications displays details about auction.
User clicks place a bid button.
Application displays place a bid page.
User fills information about the bid and clicks ok button.
Application displays bid was placed successfully.

3. User looks at the active auctions.

### Domain model

In this section we will show the details for persistence layer.

List of entities: user, item, category, auction, bid.

Details of each entity:

User: username, firstName, lastName
Item: name, description and category
Category: name.
Auction: createdOn, initialPrice, auctionStart and auctionEnd.
Bid: createdOn, amount

One user creates one Auction and one Auction is created by one user.    Auction <--> User (1-1) Aggregation.
One auction has one item and one item is associated to one auction.     Auction <--> Item (1-1) Composition.
One item has one category and one category has many items.              Category<--> Item (1-*) Aggregation.
