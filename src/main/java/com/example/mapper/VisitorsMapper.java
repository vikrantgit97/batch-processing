package com.example.mapper;


import com.example.dto.VisitorsDto;
import com.example.entity.Visitors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorsMapper {

    Visitors toVisitors(VisitorsDto visitorsDto);
}
