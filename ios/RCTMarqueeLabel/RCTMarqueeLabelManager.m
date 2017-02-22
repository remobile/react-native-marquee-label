//
//  RCTMarqueeLabel.m
//  RCTMarqueeLabel
//
//  Created by fangyunjiang on 15/11/4.
//  Copyright (c) 2015年 remobile. All rights reserved.
//
#import <React/RCTViewManager.h>
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/UIView+React.h>
#import <React/RCTFont.h>
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
RCT_EXPORT_VIEW_PROPERTY(animationDelay, CGFloat);
RCT_REMAP_VIEW_PROPERTY(color, textColor, UIColor)
RCT_CUSTOM_VIEW_PROPERTY(fontSize, NSNumber, MarqueeLabel) {
    view.font = [RCTFont updateFont:view.font withSize:json ?: @(defaultView.font.pointSize)];
}
RCT_CUSTOM_VIEW_PROPERTY(fontWeight, NSString, __unused MarqueeLabel) {
    view.font = [RCTFont updateFont:view.font withWeight:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontStyle, NSString, __unused MarqueeLabel) {
    view.font = [RCTFont updateFont:view.font withStyle:json]; // defaults to normal
}
RCT_CUSTOM_VIEW_PROPERTY(fontFamily, NSString, MarqueeLabel) {
    view.font = [RCTFont updateFont:view.font withFamily:json ?: defaultView.font.familyName];
}

@end