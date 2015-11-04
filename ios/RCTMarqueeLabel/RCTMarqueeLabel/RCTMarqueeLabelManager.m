//
//  RCTMarqueeLabel.m
//  RCTMarqueeLabel
//
//  Created by fangyunjiang on 15/11/4.
//  Copyright (c) 2015年 remobile. All rights reserved.
//
#import "RCTViewManager.h"
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"
#import "UIView+React.h"

#import "MarqueeLabel.h"

@interface RCTMarqueeLabelManager : RCTViewManager
@end


@implementation RCTConvert (RCTProgressViewManager)
RCT_ENUM_CONVERTER(MarqueeType, (@{
                                   @"MLLeftRight": @(MLLeftRight),
                                   @"MLRightLeft": @(MLRightLeft),
                                   @"MLContinuous": @(MLContinuous),
                                   @"MLContinuousReverse": @(MLContinuousReverse),
                                   }), MLContinuous, integerValue)
@end

@implementation RCTMarqueeLabelManager

RCT_EXPORT_MODULE()

- (UIView *)view {
    MarqueeLabel *view = [MarqueeLabel new];
    return view;
}

RCT_EXPORT_VIEW_PROPERTY(text, NSString);
RCT_EXPORT_VIEW_PROPERTY(marqueeType, MarqueeType);
RCT_EXPORT_VIEW_PROPERTY(scrollDuration, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(fadeLength, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(leadingBuffer, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(trailingBuffer, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(textColor, UIColor);
RCT_EXPORT_VIEW_PROPERTY(font, UIFont);

@end